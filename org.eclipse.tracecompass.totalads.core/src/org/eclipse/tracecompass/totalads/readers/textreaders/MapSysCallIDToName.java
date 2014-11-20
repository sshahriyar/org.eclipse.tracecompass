/*********************************************************************************************
 * Copyright (c) 2014-2015  Software Behaviour Analysis Lab, Concordia University, Montreal, Canada
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Eclipse Public License v1.0 License which
 * accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Syed Shariyar Murtaza -- Initial design and implementation
 **********************************************************************************************/
package org.eclipse.tracecompass.totalads.readers.textreaders;

import java.util.HashMap;

/**
 * This class is only used for lab experiments. It is a mapping of system call
 * id to name
 *
 * @author <p>
 *         Syed Shariyar Murtaza justsshary@hotmail.com
 *         </p>
 *
 */
@SuppressWarnings("nls")
public class MapSysCallIDToName {

    private static HashMap<Integer, String> sysCallIDToName;

    /**
     * Returns the system call name from an id
     *
     * @param id
     *            Id of system call
     * @return the name of the system call
     */
    public static String getSysCallName(Integer id) {
        return sysCallIDToName.get(id);
    }

    static {
        sysCallIDToName = new HashMap<>();

        sysCallIDToName.put(0, "sys_restart_syscall");
        sysCallIDToName.put(1, "sys_exit");
        sysCallIDToName.put(2, "sys_fork");
        sysCallIDToName.put(3, "sys_read");
        sysCallIDToName.put(4, "sys_write");
        sysCallIDToName.put(5, "sys_open");
        sysCallIDToName.put(6, "sys_close");
        sysCallIDToName.put(7, "sys_waitpid");
        sysCallIDToName.put(8, "sys_creat");
        sysCallIDToName.put(9, "sys_link");
        sysCallIDToName.put(10, "sys_unlink");
        sysCallIDToName.put(11, "sys_execve");
        sysCallIDToName.put(12, "sys_chdir");
        sysCallIDToName.put(13, "sys_time");
        sysCallIDToName.put(14, "sys_mknod");
        sysCallIDToName.put(15, "sys_chmod");
        sysCallIDToName.put(16, "sys_lchown16");
        sysCallIDToName.put(17, "not implemented");
        sysCallIDToName.put(18, "sys_stat");
        sysCallIDToName.put(19, "sys_lseek");
        sysCallIDToName.put(20, "sys_getpid");
        sysCallIDToName.put(21, "sys_mount");
        sysCallIDToName.put(22, "sys_oldumount");
        sysCallIDToName.put(23, "sys_setuid16");
        sysCallIDToName.put(24, "sys_getuid16");
        sysCallIDToName.put(25, "sys_stime");
        sysCallIDToName.put(26, "sys_ptrace");
        sysCallIDToName.put(27, "sys_alarm");
        sysCallIDToName.put(28, "sys_fstat");
        sysCallIDToName.put(29, "sys_pause");
        sysCallIDToName.put(30, "sys_utime");
        sysCallIDToName.put(31, "not implemented");
        sysCallIDToName.put(32, "not implemented");
        sysCallIDToName.put(33, "sys_access");
        sysCallIDToName.put(34, "sys_nice");
        sysCallIDToName.put(35, "not implemented");
        sysCallIDToName.put(36, "sys_sync");
        sysCallIDToName.put(37, "sys_kill");
        sysCallIDToName.put(38, "sys_rename");
        sysCallIDToName.put(39, "sys_mkdir");
        sysCallIDToName.put(40, "sys_rmdir");
        sysCallIDToName.put(41, "sys_dup");
        sysCallIDToName.put(42, "sys_pipe");
        sysCallIDToName.put(43, "sys_times");
        sysCallIDToName.put(44, "not implemented");
        sysCallIDToName.put(45, "sys_brk");
        sysCallIDToName.put(46, "sys_setgid16");
        sysCallIDToName.put(47, "sys_getgid16");
        sysCallIDToName.put(48, "sys_signal");
        sysCallIDToName.put(49, "sys_geteuid16");
        sysCallIDToName.put(50, "sys_getegid16");
        sysCallIDToName.put(51, "sys_acct");
        sysCallIDToName.put(52, "sys_umount");
        sysCallIDToName.put(53, "not implemented");
        sysCallIDToName.put(54, "sys_ioctl");
        sysCallIDToName.put(55, "sys_fcntl");
        sysCallIDToName.put(56, "not implemented");
        sysCallIDToName.put(57, "sys_setpgid");
        sysCallIDToName.put(58, "not implemented");
        sysCallIDToName.put(59, "sys_olduname");
        sysCallIDToName.put(60, "sys_umask");
        sysCallIDToName.put(61, "sys_chroot");
        sysCallIDToName.put(62, "sys_ustat");
        sysCallIDToName.put(63, "sys_dup2");
        sysCallIDToName.put(64, "sys_getppid");
        sysCallIDToName.put(65, "sys_getpgrp");
        sysCallIDToName.put(66, "sys_setsid");
        sysCallIDToName.put(67, "sys_sigaction");
        sysCallIDToName.put(68, "sys_sgetmask");
        sysCallIDToName.put(69, "sys_ssetmask");
        sysCallIDToName.put(70, "sys_setreuid16");
        sysCallIDToName.put(71, "sys_setregid16");
        sysCallIDToName.put(72, "sys_sigsuspend");
        sysCallIDToName.put(73, "sys_sigpending");
        sysCallIDToName.put(74, "sys_sethostname");
        sysCallIDToName.put(75, "sys_setrlimit");
        sysCallIDToName.put(76, "sys_old_getrlimit");
        sysCallIDToName.put(77, "sys_getrusage");
        sysCallIDToName.put(78, "sys_gettimeofday");
        sysCallIDToName.put(79, "sys_settimeofday");
        sysCallIDToName.put(80, "sys_getgroups16");
        sysCallIDToName.put(81, "sys_setgroups16");
        sysCallIDToName.put(82, "sys_old_select");
        sysCallIDToName.put(83, "sys_symlink");
        sysCallIDToName.put(84, "sys_lstat");
        sysCallIDToName.put(85, "sys_readlink");
        sysCallIDToName.put(86, "sys_uselib");
        sysCallIDToName.put(87, "sys_swapon");
        sysCallIDToName.put(88, "sys_reboot");
        sysCallIDToName.put(89, "sys_old_readdir");
        sysCallIDToName.put(90, "sys_old_mmap");
        sysCallIDToName.put(91, "sys_munmap");
        sysCallIDToName.put(92, "sys_truncate");
        sysCallIDToName.put(93, "sys_ftruncate");
        sysCallIDToName.put(94, "sys_fchmod");
        sysCallIDToName.put(95, "sys_fchown16");
        sysCallIDToName.put(96, "sys_getpriority");
        sysCallIDToName.put(97, "sys_setpriority");
        sysCallIDToName.put(98, "not implemented");
        sysCallIDToName.put(99, "sys_statfs");
        sysCallIDToName.put(100, "sys_fstatfs");
        sysCallIDToName.put(101, "sys_ioperm");
        sysCallIDToName.put(102, "sys_socketcall");
        sysCallIDToName.put(103, "sys_syslog");
        sysCallIDToName.put(104, "sys_setitimer");
        sysCallIDToName.put(105, "sys_getitimer");
        sysCallIDToName.put(106, "sys_newstat");
        sysCallIDToName.put(107, "sys_newlstat");
        sysCallIDToName.put(108, "sys_newfstat");
        sysCallIDToName.put(109, "sys_uname");
        sysCallIDToName.put(110, "sys_iopl");
        sysCallIDToName.put(111, "sys_vhangup");
        sysCallIDToName.put(112, "not implemented");
        sysCallIDToName.put(113, "sys_vm86old");
        sysCallIDToName.put(114, "sys_wait4");
        sysCallIDToName.put(115, "sys_swapoff");
        sysCallIDToName.put(116, "sys_sysinfo");
        sysCallIDToName.put(117, "sys_ipc");
        sysCallIDToName.put(118, "sys_fsync");
        sysCallIDToName.put(119, "sys_sigreturn");
        sysCallIDToName.put(120, "sys_clone");
        sysCallIDToName.put(121, "sys_setdomainname");
        sysCallIDToName.put(122, "sys_newuname");
        sysCallIDToName.put(123, "sys_modify_ldt");
        sysCallIDToName.put(124, "sys_adjtimex");
        sysCallIDToName.put(125, "sys_mprotect");
        sysCallIDToName.put(126, "sys_sigprocmask");
        sysCallIDToName.put(127, "not implemented");
        sysCallIDToName.put(128, "sys_init_module");
        sysCallIDToName.put(129, "sys_delete_module");
        sysCallIDToName.put(130, "not implemented");
        sysCallIDToName.put(131, "sys_quotactl");
        sysCallIDToName.put(132, "sys_getpgid");
        sysCallIDToName.put(133, "sys_fchdir");
        sysCallIDToName.put(134, "sys_bdflush");
        sysCallIDToName.put(135, "sys_sysfs");
        sysCallIDToName.put(136, "sys_personality");
        sysCallIDToName.put(137, "not implemented");
        sysCallIDToName.put(138, "sys_setfsuid16");
        sysCallIDToName.put(139, "sys_setfsgid16");
        sysCallIDToName.put(140, "sys_llseek");
        sysCallIDToName.put(141, "sys_getdents");
        sysCallIDToName.put(142, "sys_select");
        sysCallIDToName.put(143, "sys_flock");
        sysCallIDToName.put(144, "sys_msync");
        sysCallIDToName.put(145, "sys_readv");
        sysCallIDToName.put(146, "sys_writev");
        sysCallIDToName.put(147, "sys_getsid");
        sysCallIDToName.put(148, "sys_fdatasync");
        sysCallIDToName.put(149, "sys_sysctl");
        sysCallIDToName.put(150, "sys_mlock");
        sysCallIDToName.put(151, "sys_munlock");
        sysCallIDToName.put(152, "sys_mlockall");
        sysCallIDToName.put(153, "sys_munlockall");
        sysCallIDToName.put(154, "sys_sched_setparam");
        sysCallIDToName.put(155, "sys_sched_getparam");
        sysCallIDToName.put(156, "sys_sched_setscheduler");
        sysCallIDToName.put(157, "sys_sched_getscheduler");
        sysCallIDToName.put(158, "sys_sched_yield");
        sysCallIDToName.put(159, "sys_sched_get_priority_max");
        sysCallIDToName.put(160, "sys_sched_get_priority_min");
        sysCallIDToName.put(161, "sys_sched_rr_get_interval");
        sysCallIDToName.put(162, "sys_nanosleep");
        sysCallIDToName.put(163, "sys_mremap");
        sysCallIDToName.put(164, "sys_setresuid16");
        sysCallIDToName.put(165, "sys_getresuid16");
        sysCallIDToName.put(166, "sys_vm86");
        sysCallIDToName.put(167, "not implemented");
        sysCallIDToName.put(168, "sys_poll");
        sysCallIDToName.put(169, "sys_nfsservctl");
        sysCallIDToName.put(170, "sys_setresgid16");
        sysCallIDToName.put(171, "sys_getresgid16");
        sysCallIDToName.put(172, "sys_prctl");
        sysCallIDToName.put(173, "sys_rt_sigreturn");
        sysCallIDToName.put(174, "sys_rt_sigaction");
        sysCallIDToName.put(175, "sys_rt_sigprocmask");
        sysCallIDToName.put(176, "sys_rt_sigpending");
        sysCallIDToName.put(177, "sys_rt_sigtimedwait");
        sysCallIDToName.put(178, "sys_rt_sigqueueinfo");
        sysCallIDToName.put(179, "sys_rt_sigsuspend");
        sysCallIDToName.put(180, "sys_pread64");
        sysCallIDToName.put(181, "sys_pwrite64");
        sysCallIDToName.put(182, "sys_chown16");
        sysCallIDToName.put(183, "sys_getcwd");
        sysCallIDToName.put(184, "sys_capget");
        sysCallIDToName.put(185, "sys_capset");
        sysCallIDToName.put(186, "sys_sigaltstack");
        sysCallIDToName.put(187, "sys_sendfile");
        sysCallIDToName.put(188, "not implemented");
        sysCallIDToName.put(189, "not implemented");
        sysCallIDToName.put(190, "sys_vfork");
        sysCallIDToName.put(191, "sys_getrlimit");
        sysCallIDToName.put(192, "sys_mmap_pgoff");
        sysCallIDToName.put(193, "sys_truncate64");
        sysCallIDToName.put(194, "sys_ftruncate64");
        sysCallIDToName.put(195, "sys_stat64");
        sysCallIDToName.put(196, "sys_lstat64");
        sysCallIDToName.put(197, "sys_fstat64");
        sysCallIDToName.put(198, "sys_lchown");
        sysCallIDToName.put(199, "sys_getuid");
        sysCallIDToName.put(200, "sys_getgid");
        sysCallIDToName.put(201, "sys_geteuid");
        sysCallIDToName.put(202, "sys_getegid");
        sysCallIDToName.put(203, "sys_setreuid");
        sysCallIDToName.put(204, "sys_setregid");
        sysCallIDToName.put(205, "sys_getgroups");
        sysCallIDToName.put(206, "sys_setgroups");
        sysCallIDToName.put(207, "sys_fchown");
        sysCallIDToName.put(208, "sys_setresuid");
        sysCallIDToName.put(209, "sys_getresuid");
        sysCallIDToName.put(210, "sys_setresgid");
        sysCallIDToName.put(211, "sys_getresgid");
        sysCallIDToName.put(212, "sys_chown");
        sysCallIDToName.put(213, "sys_setuid");
        sysCallIDToName.put(214, "sys_setgid");
        sysCallIDToName.put(215, "sys_setfsuid");
        sysCallIDToName.put(216, "sys_setfsgid");
        sysCallIDToName.put(217, "sys_pivot_root");
        sysCallIDToName.put(218, "sys_mincore");
        sysCallIDToName.put(219, "sys_madvise");
        sysCallIDToName.put(220, "sys_getdents64");
        sysCallIDToName.put(221, "sys_fcntl64");
        sysCallIDToName.put(222, "not implemented");
        sysCallIDToName.put(223, "not implemented");
        sysCallIDToName.put(224, "sys_gettid");
        sysCallIDToName.put(225, "sys_readahead");
        sysCallIDToName.put(226, "sys_setxattr");
        sysCallIDToName.put(227, "sys_lsetxattr");
        sysCallIDToName.put(228, "sys_fsetxattr");
        sysCallIDToName.put(229, "sys_getxattr");
        sysCallIDToName.put(230, "sys_lgetxattr");
        sysCallIDToName.put(231, "sys_fgetxattr");
        sysCallIDToName.put(232, "sys_listxattr");
        sysCallIDToName.put(233, "sys_llistxattr");
        sysCallIDToName.put(234, "sys_flistxattr");
        sysCallIDToName.put(235, "sys_removexattr");
        sysCallIDToName.put(236, "sys_lremovexattr");
        sysCallIDToName.put(237, "sys_fremovexattr");
        sysCallIDToName.put(238, "sys_tkill");
        sysCallIDToName.put(239, "sys_sendfile64");
        sysCallIDToName.put(240, "sys_futex");
        sysCallIDToName.put(241, "sys_sched_setaffinity");
        sysCallIDToName.put(242, "sys_sched_getaffinity");
        sysCallIDToName.put(243, "sys_set_thread_area");
        sysCallIDToName.put(244, "sys_get_thread_area");
        sysCallIDToName.put(245, "sys_io_setup");
        sysCallIDToName.put(246, "sys_io_destroy");
        sysCallIDToName.put(247, "sys_io_getevents");
        sysCallIDToName.put(248, "sys_io_submit");
        sysCallIDToName.put(249, "sys_io_cancel");
        sysCallIDToName.put(250, "sys_fadvise64");
        sysCallIDToName.put(251, "not implemented");
        sysCallIDToName.put(252, "sys_exit_group");
        sysCallIDToName.put(253, "sys_lookup_dcookie");
        sysCallIDToName.put(254, "sys_epoll_create");
        sysCallIDToName.put(255, "sys_epoll_ctl");
        sysCallIDToName.put(256, "sys_epoll_wait");
        sysCallIDToName.put(257, "sys_remap_file_pages");
        sysCallIDToName.put(258, "sys_set_tid_address");
        sysCallIDToName.put(259, "sys_timer_create");
        sysCallIDToName.put(260, "sys_timer_settime");
        sysCallIDToName.put(261, "sys_timer_gettime");
        sysCallIDToName.put(262, "sys_timer_getoverrun");
        sysCallIDToName.put(263, "sys_timer_delete");
        sysCallIDToName.put(264, "sys_clock_settime");
        sysCallIDToName.put(265, "sys_clock_gettime");
        sysCallIDToName.put(266, "sys_clock_getres");
        sysCallIDToName.put(267, "sys_clock_nanosleep");
        sysCallIDToName.put(268, "sys_statfs64");
        sysCallIDToName.put(269, "sys_fstatfs64");
        sysCallIDToName.put(270, "sys_tgkill");
        sysCallIDToName.put(271, "sys_utimes");
        sysCallIDToName.put(272, "sys_fadvise64_64");
        sysCallIDToName.put(273, "not implemented");
        sysCallIDToName.put(274, "sys_mbind");
        sysCallIDToName.put(275, "sys_get_mempolicy");
        sysCallIDToName.put(276, "sys_set_mempolicy");
        sysCallIDToName.put(277, "sys_mq_open");
        sysCallIDToName.put(278, "sys_mq_unlink");
        sysCallIDToName.put(279, "sys_mq_timedsend");
        sysCallIDToName.put(280, "sys_mq_timedreceive");
        sysCallIDToName.put(281, "sys_mq_notify");
        sysCallIDToName.put(282, "sys_mq_getsetattr");
        sysCallIDToName.put(283, "sys_kexec_load");
        sysCallIDToName.put(284, "sys_waitid");
        sysCallIDToName.put(285, "not implemented");
        sysCallIDToName.put(286, "sys_add_key");
        sysCallIDToName.put(287, "sys_request_key");
        sysCallIDToName.put(288, "sys_keyctl");
        sysCallIDToName.put(289, "sys_ioprio_set");
        sysCallIDToName.put(290, "sys_ioprio_get");
        sysCallIDToName.put(291, "sys_inotify_init");
        sysCallIDToName.put(292, "sys_inotify_add_watch");
        sysCallIDToName.put(293, "sys_inotify_rm_watch");
        sysCallIDToName.put(294, "sys_migrate_pages");
        sysCallIDToName.put(295, "sys_openat");
        sysCallIDToName.put(296, "sys_mkdirat");
        sysCallIDToName.put(297, "sys_mknodat");
        sysCallIDToName.put(298, "sys_fchownat");
        sysCallIDToName.put(299, "sys_futimesat");
        sysCallIDToName.put(300, "sys_fstatat64");
        sysCallIDToName.put(301, "sys_unlinkat");
        sysCallIDToName.put(302, "sys_renameat");
        sysCallIDToName.put(303, "sys_linkat");
        sysCallIDToName.put(304, "sys_symlinkat");
        sysCallIDToName.put(305, "sys_readlinkat");
        sysCallIDToName.put(306, "sys_fchmodat");
        sysCallIDToName.put(307, "sys_faccessat");
        sysCallIDToName.put(308, "sys_pselect6");
        sysCallIDToName.put(309, "sys_ppoll");
        sysCallIDToName.put(310, "sys_unshare");
        sysCallIDToName.put(311, "sys_set_robust_list");
        sysCallIDToName.put(312, "sys_get_robust_list");
        sysCallIDToName.put(313, "sys_splice");
        sysCallIDToName.put(314, "sys_sync_file_range");
        sysCallIDToName.put(315, "sys_tee");
        sysCallIDToName.put(316, "sys_vmsplice");
        sysCallIDToName.put(317, "sys_move_pages");
        sysCallIDToName.put(318, "sys_getcpu");
        sysCallIDToName.put(319, "sys_epoll_pwait");
        sysCallIDToName.put(320, "sys_utimensat");
        sysCallIDToName.put(321, "sys_signalfd");
        sysCallIDToName.put(322, "sys_timerfd_create");
        sysCallIDToName.put(323, "sys_eventfd");
        sysCallIDToName.put(324, "sys_fallocate");
        sysCallIDToName.put(325, "sys_timerfd_settime");
        sysCallIDToName.put(326, "sys_timerfd_gettime");
        sysCallIDToName.put(327, "sys_signalfd4");
        sysCallIDToName.put(328, "sys_eventfd2");
        sysCallIDToName.put(329, "sys_epoll_create1");
        sysCallIDToName.put(330, "sys_dup3");
        sysCallIDToName.put(331, "sys_pipe2");
        sysCallIDToName.put(332, "sys_inotify_init1");
        sysCallIDToName.put(333, "sys_preadv");
        sysCallIDToName.put(334, "sys_pwritev");
        sysCallIDToName.put(335, "sys_rt_tgsigqueueinfo");
        sysCallIDToName.put(336, "sys_perf_event_open");
        sysCallIDToName.put(337, "sys_recvmmsg");

    }
    /**
     * 64 bit system call mapping
     */

    /*
     * static { sysCallIDToName=new HashMap<>();
     * sysCallIDToName.put(0,"sys_read"); sysCallIDToName.put(1,"sys_write");
     * sysCallIDToName.put(2,"sys_open"); sysCallIDToName.put(3,"sys_close");
     * sysCallIDToName.put(4,"sys_newstat");
     * sysCallIDToName.put(5,"sys_newfstat");
     * sysCallIDToName.put(6,"sys_newlstat"); sysCallIDToName.put(7,"sys_poll");
     * sysCallIDToName.put(8,"sys_lseek"); sysCallIDToName.put(9,"sys_mmap");
     * sysCallIDToName.put(10,"sys_mprotect");
     * sysCallIDToName.put(11,"sys_munmap"); sysCallIDToName.put(12,"sys_brk");
     * sysCallIDToName.put(13,"sys_rt_sigaction");
     * sysCallIDToName.put(14,"sys_rt_sigprocmask");
     * sysCallIDToName.put(15,"stub_rt_sigreturn");
     * sysCallIDToName.put(16,"sys_ioctl");
     * sysCallIDToName.put(17,"sys_pread64");
     * sysCallIDToName.put(18,"sys_pwrite64");
     * sysCallIDToName.put(19,"sys_readv");
     * sysCallIDToName.put(20,"sys_writev");
     * sysCallIDToName.put(21,"sys_access"); sysCallIDToName.put(22,"sys_pipe");
     * sysCallIDToName.put(23,"sys_select");
     * sysCallIDToName.put(24,"sys_sched_yield");
     * sysCallIDToName.put(25,"sys_mremap");
     * sysCallIDToName.put(26,"sys_msync");
     * sysCallIDToName.put(27,"sys_mincore");
     * sysCallIDToName.put(28,"sys_madvise");
     * sysCallIDToName.put(29,"sys_shmget");
     * sysCallIDToName.put(30,"sys_shmat");
     * sysCallIDToName.put(31,"sys_shmctl"); sysCallIDToName.put(32,"sys_dup");
     * sysCallIDToName.put(33,"sys_dup2"); sysCallIDToName.put(34,"sys_pause");
     * sysCallIDToName.put(35,"sys_nanosleep");
     * sysCallIDToName.put(36,"sys_getitimer");
     * sysCallIDToName.put(37,"sys_alarm");
     * sysCallIDToName.put(38,"sys_setitimer");
     * sysCallIDToName.put(39,"sys_getpid");
     * sysCallIDToName.put(40,"sys_sendfile64");
     * sysCallIDToName.put(41,"sys_socket");
     * sysCallIDToName.put(42,"sys_connect");
     * sysCallIDToName.put(43,"sys_accept");
     * sysCallIDToName.put(44,"sys_sendto");
     * sysCallIDToName.put(45,"sys_recvfrom");
     * sysCallIDToName.put(46,"sys_sendmsg");
     * sysCallIDToName.put(47,"sys_recvmsg");
     * sysCallIDToName.put(48,"sys_shutdown");
     * sysCallIDToName.put(49,"sys_bind"); sysCallIDToName.put(50,"sys_listen");
     * sysCallIDToName.put(51,"sys_getsockname");
     * sysCallIDToName.put(52,"sys_getpeername");
     * sysCallIDToName.put(53,"sys_socketpair");
     * sysCallIDToName.put(54,"sys_setsockopt");
     * sysCallIDToName.put(55,"sys_getsockopt");
     * sysCallIDToName.put(56,"stub_clone");
     * sysCallIDToName.put(57,"stub_fork");
     * sysCallIDToName.put(58,"stub_vfork");
     * sysCallIDToName.put(59,"stub_execve");
     * sysCallIDToName.put(60,"sys_exit"); sysCallIDToName.put(61,"sys_wait4");
     * sysCallIDToName.put(62,"sys_kill");
     * sysCallIDToName.put(63,"sys_newuname");
     * sysCallIDToName.put(64,"sys_semget");
     * sysCallIDToName.put(65,"sys_semop");
     * sysCallIDToName.put(66,"sys_semctl");
     * sysCallIDToName.put(67,"sys_shmdt");
     * sysCallIDToName.put(68,"sys_msgget");
     * sysCallIDToName.put(69,"sys_msgsnd");
     * sysCallIDToName.put(70,"sys_msgrcv");
     * sysCallIDToName.put(71,"sys_msgctl");
     * sysCallIDToName.put(72,"sys_fcntl"); sysCallIDToName.put(73,"sys_flock");
     * sysCallIDToName.put(74,"sys_fsync");
     * sysCallIDToName.put(75,"sys_fdatasync");
     * sysCallIDToName.put(76,"sys_truncate");
     * sysCallIDToName.put(77,"sys_ftruncate");
     * sysCallIDToName.put(78,"sys_getdents");
     * sysCallIDToName.put(79,"sys_getcwd");
     * sysCallIDToName.put(80,"sys_chdir");
     * sysCallIDToName.put(81,"sys_fchdir");
     * sysCallIDToName.put(82,"sys_rename");
     * sysCallIDToName.put(83,"sys_mkdir"); sysCallIDToName.put(84,"sys_rmdir");
     * sysCallIDToName.put(85,"sys_creat"); sysCallIDToName.put(86,"sys_link");
     * sysCallIDToName.put(87,"sys_unlink");
     * sysCallIDToName.put(88,"sys_symlink");
     * sysCallIDToName.put(89,"sys_readlink");
     * sysCallIDToName.put(90,"sys_chmod");
     * sysCallIDToName.put(91,"sys_fchmod");
     * sysCallIDToName.put(92,"sys_chown");
     * sysCallIDToName.put(93,"sys_fchown");
     * sysCallIDToName.put(94,"sys_lchown");
     * sysCallIDToName.put(95,"sys_umask");
     * sysCallIDToName.put(96,"sys_gettimeofday");
     * sysCallIDToName.put(97,"sys_getrlimit");
     * sysCallIDToName.put(98,"sys_getrusage");
     * sysCallIDToName.put(99,"sys_sysinfo");
     * sysCallIDToName.put(100,"sys_times");
     * sysCallIDToName.put(101,"sys_ptrace");
     * sysCallIDToName.put(102,"sys_getuid");
     * sysCallIDToName.put(103,"sys_syslog");
     * sysCallIDToName.put(104,"sys_getgid");
     * sysCallIDToName.put(105,"sys_setuid");
     * sysCallIDToName.put(106,"sys_setgid");
     * sysCallIDToName.put(107,"sys_geteuid");
     * sysCallIDToName.put(108,"sys_getegid");
     * sysCallIDToName.put(109,"sys_setpgid");
     * sysCallIDToName.put(110,"sys_getppid");
     * sysCallIDToName.put(111,"sys_getpgrp");
     * sysCallIDToName.put(112,"sys_setsid");
     * sysCallIDToName.put(113,"sys_setreuid");
     * sysCallIDToName.put(114,"sys_setregid");
     * sysCallIDToName.put(115,"sys_getgroups");
     * sysCallIDToName.put(116,"sys_setgroups");
     * sysCallIDToName.put(117,"sys_setresuid");
     * sysCallIDToName.put(118,"sys_getresuid");
     * sysCallIDToName.put(119,"sys_setresgid");
     * sysCallIDToName.put(120,"sys_getresgid");
     * sysCallIDToName.put(121,"sys_getpgid");
     * sysCallIDToName.put(122,"sys_setfsuid");
     * sysCallIDToName.put(123,"sys_setfsgid");
     * sysCallIDToName.put(124,"sys_getsid");
     * sysCallIDToName.put(125,"sys_capget");
     * sysCallIDToName.put(126,"sys_capset");
     * sysCallIDToName.put(127,"sys_rt_sigpending");
     * sysCallIDToName.put(128,"sys_rt_sigtimedwait");
     * sysCallIDToName.put(129,"sys_rt_sigqueueinfo");
     * sysCallIDToName.put(130,"sys_rt_sigsuspend");
     * sysCallIDToName.put(131,"stub_sigaltstack");
     * sysCallIDToName.put(132,"sys_utime");
     * sysCallIDToName.put(133,"sys_mknod");
     * sysCallIDToName.put(134,"sys_ni_syscall");
     * sysCallIDToName.put(135,"sys_personality");
     * sysCallIDToName.put(136,"sys_ustat");
     * sysCallIDToName.put(137,"sys_statfs");
     * sysCallIDToName.put(138,"sys_fstatfs");
     * sysCallIDToName.put(139,"sys_sysfs");
     * sysCallIDToName.put(140,"sys_getpriority");
     * sysCallIDToName.put(141,"sys_setpriority");
     * sysCallIDToName.put(142,"sys_sched_setparam");
     * sysCallIDToName.put(143,"sys_sched_getparam");
     * sysCallIDToName.put(144,"sys_sched_setscheduler");
     * sysCallIDToName.put(145,"sys_sched_getscheduler");
     * sysCallIDToName.put(146,"sys_sched_get_priority_max");
     * sysCallIDToName.put(147,"sys_sched_get_priority_min");
     * sysCallIDToName.put(148,"sys_sched_rr_get_interval");
     * sysCallIDToName.put(149,"sys_mlock");
     * sysCallIDToName.put(150,"sys_munlock");
     * sysCallIDToName.put(151,"sys_mlockall");
     * sysCallIDToName.put(152,"sys_munlockall");
     * sysCallIDToName.put(153,"sys_vhangup");
     * sysCallIDToName.put(154,"sys_modify_ldt");
     * sysCallIDToName.put(155,"sys_pivot_root");
     * sysCallIDToName.put(156,"sys_sysctl");
     * sysCallIDToName.put(157,"sys_prctl");
     * sysCallIDToName.put(158,"sys_arch_prctl");
     * sysCallIDToName.put(159,"sys_adjtimex");
     * sysCallIDToName.put(160,"sys_setrlimit");
     * sysCallIDToName.put(161,"sys_chroot");
     * sysCallIDToName.put(162,"sys_sync"); sysCallIDToName.put(163,"sys_acct");
     * sysCallIDToName.put(164,"sys_settimeofday");
     * sysCallIDToName.put(165,"sys_mount");
     * sysCallIDToName.put(166,"sys_umount");
     * sysCallIDToName.put(167,"sys_swapon");
     * sysCallIDToName.put(168,"sys_swapoff");
     * sysCallIDToName.put(169,"sys_reboot");
     * sysCallIDToName.put(170,"sys_sethostname");
     * sysCallIDToName.put(171,"sys_setdomainname");
     * sysCallIDToName.put(172,"stub_iopl");
     * sysCallIDToName.put(173,"sys_ioperm");
     * sysCallIDToName.put(174,"sys_ni_syscall");
     * sysCallIDToName.put(175,"sys_init_module");
     * sysCallIDToName.put(176,"sys_delete_module");
     * sysCallIDToName.put(177,"sys_ni_syscall");
     * sysCallIDToName.put(178,"sys_ni_syscall");
     * sysCallIDToName.put(179,"sys_quotactl");
     * sysCallIDToName.put(180,"sys_ni_syscall");
     * sysCallIDToName.put(181,"sys_ni_syscall");
     * sysCallIDToName.put(182,"sys_ni_syscall");
     * sysCallIDToName.put(183,"sys_ni_syscall");
     * sysCallIDToName.put(184,"sys_ni_syscall");
     * sysCallIDToName.put(185,"sys_ni_syscall");
     * sysCallIDToName.put(186,"sys_gettid");
     * sysCallIDToName.put(187,"sys_readahead");
     * sysCallIDToName.put(188,"sys_setxattr");
     * sysCallIDToName.put(189,"sys_lsetxattr");
     * sysCallIDToName.put(190,"sys_fsetxattr");
     * sysCallIDToName.put(191,"sys_getxattr");
     * sysCallIDToName.put(192,"sys_lgetxattr");
     * sysCallIDToName.put(193,"sys_fgetxattr");
     * sysCallIDToName.put(194,"sys_listxattr");
     * sysCallIDToName.put(195,"sys_llistxattr");
     * sysCallIDToName.put(196,"sys_flistxattr");
     * sysCallIDToName.put(197,"sys_removexattr");
     * sysCallIDToName.put(198,"sys_lremovexattr");
     * sysCallIDToName.put(199,"sys_fremovexattr");
     * sysCallIDToName.put(200,"sys_tkill");
     * sysCallIDToName.put(201,"sys_time");
     * sysCallIDToName.put(202,"sys_futex");
     * sysCallIDToName.put(203,"sys_sched_setaffinity");
     * sysCallIDToName.put(204,"sys_sched_getaffinity");
     * sysCallIDToName.put(205,"sys_ni_syscall");
     * sysCallIDToName.put(206,"sys_io_setup");
     * sysCallIDToName.put(207,"sys_io_destroy");
     * sysCallIDToName.put(208,"sys_io_getevents");
     * sysCallIDToName.put(209,"sys_io_submit");
     * sysCallIDToName.put(210,"sys_io_cancel");
     * sysCallIDToName.put(211,"sys_ni_syscall");
     * sysCallIDToName.put(212,"sys_lookup_dcookie");
     * sysCallIDToName.put(213,"sys_epoll_create");
     * sysCallIDToName.put(214,"sys_ni_syscall");
     * sysCallIDToName.put(215,"sys_ni_syscall");
     * sysCallIDToName.put(216,"sys_remap_file_pages");
     * sysCallIDToName.put(217,"sys_getdents64");
     * sysCallIDToName.put(218,"sys_set_tid_address");
     * sysCallIDToName.put(219,"sys_restart_syscall");
     * sysCallIDToName.put(220,"sys_semtimedop");
     * sysCallIDToName.put(221,"sys_fadvise64");
     * sysCallIDToName.put(222,"sys_timer_create");
     * sysCallIDToName.put(223,"sys_timer_settime");
     * sysCallIDToName.put(224,"sys_timer_gettime");
     * sysCallIDToName.put(225,"sys_timer_getoverrun");
     * sysCallIDToName.put(226,"sys_timer_delete");
     * sysCallIDToName.put(227,"sys_clock_settime");
     * sysCallIDToName.put(228,"sys_clock_gettime");
     * sysCallIDToName.put(229,"sys_clock_getres");
     * sysCallIDToName.put(230,"sys_clock_nanosleep");
     * sysCallIDToName.put(231,"sys_exit_group");
     * sysCallIDToName.put(232,"sys_epoll_wait");
     * sysCallIDToName.put(233,"sys_epoll_ctl");
     * sysCallIDToName.put(234,"sys_tgkill");
     * sysCallIDToName.put(235,"sys_utimes");
     * sysCallIDToName.put(236,"sys_ni_syscall");
     * sysCallIDToName.put(237,"sys_mbind");
     * sysCallIDToName.put(238,"sys_set_mempolicy");
     * sysCallIDToName.put(239,"sys_get_mempolicy");
     * sysCallIDToName.put(240,"sys_mq_open");
     * sysCallIDToName.put(241,"sys_mq_unlink");
     * sysCallIDToName.put(242,"sys_mq_timedsend");
     * sysCallIDToName.put(243,"sys_mq_timedreceive");
     * sysCallIDToName.put(244,"sys_mq_notify");
     * sysCallIDToName.put(245,"sys_mq_getsetattr");
     * sysCallIDToName.put(246,"sys_kexec_load");
     * sysCallIDToName.put(247,"sys_waitid");
     * sysCallIDToName.put(248,"sys_add_key");
     * sysCallIDToName.put(249,"sys_request_key");
     * sysCallIDToName.put(250,"sys_keyctl");
     * sysCallIDToName.put(251,"sys_ioprio_set");
     * sysCallIDToName.put(252,"sys_ioprio_get");
     * sysCallIDToName.put(253,"sys_inotify_init");
     * sysCallIDToName.put(254,"sys_inotify_add_watch");
     * sysCallIDToName.put(255,"sys_inotify_rm_watch");
     * sysCallIDToName.put(256,"sys_migrate_pages");
     * sysCallIDToName.put(257,"sys_openat");
     * sysCallIDToName.put(258,"sys_mkdirat");
     * sysCallIDToName.put(259,"sys_mknodat");
     * sysCallIDToName.put(260,"sys_fchownat");
     * sysCallIDToName.put(261,"sys_futimesat");
     * sysCallIDToName.put(262,"sys_newfstatat");
     * sysCallIDToName.put(263,"sys_unlinkat");
     * sysCallIDToName.put(264,"sys_renameat");
     * sysCallIDToName.put(265,"sys_linkat");
     * sysCallIDToName.put(266,"sys_symlinkat");
     * sysCallIDToName.put(267,"sys_readlinkat");
     * sysCallIDToName.put(268,"sys_fchmodat");
     * sysCallIDToName.put(269,"sys_faccessat");
     * sysCallIDToName.put(270,"sys_pselect6");
     * sysCallIDToName.put(271,"sys_ppoll");
     * sysCallIDToName.put(272,"sys_unshare");
     * sysCallIDToName.put(273,"sys_set_robust_list");
     * sysCallIDToName.put(274,"sys_get_robust_list");
     * sysCallIDToName.put(275,"sys_splice");
     * sysCallIDToName.put(276,"sys_tee");
     * sysCallIDToName.put(277,"sys_sync_file_range");
     * sysCallIDToName.put(278,"sys_vmsplice");
     * sysCallIDToName.put(279,"sys_move_pages");
     * sysCallIDToName.put(280,"sys_utimensat");
     * sysCallIDToName.put(281,"sys_epoll_pwait");
     * sysCallIDToName.put(282,"sys_signalfd");
     * sysCallIDToName.put(283,"sys_timerfd_create");
     * sysCallIDToName.put(284,"sys_eventfd");
     * sysCallIDToName.put(285,"sys_fallocate");
     * sysCallIDToName.put(286,"sys_timerfd_settime");
     * sysCallIDToName.put(287,"sys_timerfd_gettime");
     * sysCallIDToName.put(288,"sys_accept4");
     * sysCallIDToName.put(289,"sys_signalfd4");
     * sysCallIDToName.put(290,"sys_eventfd2");
     * sysCallIDToName.put(291,"sys_epoll_create1");
     * sysCallIDToName.put(292,"sys_dup3");
     * sysCallIDToName.put(293,"sys_pipe2");
     * sysCallIDToName.put(294,"sys_inotify_init1");
     * sysCallIDToName.put(295,"sys_preadv");
     * sysCallIDToName.put(296,"sys_pwritev");
     * sysCallIDToName.put(297,"sys_rt_tgsigqueueinfo");
     * sysCallIDToName.put(298,"sys_perf_event_open");
     * sysCallIDToName.put(299,"sys_recvmmsg");
     * sysCallIDToName.put(300,"sys_fanotify_init");
     * sysCallIDToName.put(301,"sys_fanotify_mark");
     * sysCallIDToName.put(302,"sys_prlimit64");
     * sysCallIDToName.put(303,"sys_name_to_handle_at");
     * sysCallIDToName.put(304,"sys_open_by_handle_at");
     * sysCallIDToName.put(305,"sys_clock_adjtime");
     * sysCallIDToName.put(306,"sys_syncfs");
     * sysCallIDToName.put(307,"sys_sendmmsg");
     * sysCallIDToName.put(308,"sys_setns");
     * sysCallIDToName.put(309,"sys_getcpu");
     * sysCallIDToName.put(310,"sys_process_vm_readv");
     * sysCallIDToName.put(311,"sys_process_vm_writev");
     *
     *
     * }
     */

}
